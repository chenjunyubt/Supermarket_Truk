/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Accessor;
import com.google.gson.annotations.Optional;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Type adapter that reflects over the fields and methods of a class.
 */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
	private final ConstructorConstructor constructorConstructor;
	private final FieldNamingStrategy fieldNamingPolicy;
	private final Excluder excluder;

	public ReflectiveTypeAdapterFactory(
			ConstructorConstructor constructorConstructor,
			FieldNamingStrategy fieldNamingPolicy, Excluder excluder) {
		this.constructorConstructor = constructorConstructor;
		this.fieldNamingPolicy = fieldNamingPolicy;
		this.excluder = excluder;
	}

	public boolean excludeField(Field f, boolean serialize) {
		return !excluder.excludeClass(f.getType(), serialize)
				&& !excluder.excludeField(f, serialize);
	}

	private String getFieldName(Field f) {
		SerializedName serializedName = f.getAnnotation(SerializedName.class);
		return serializedName == null ? fieldNamingPolicy.translateName(f)
				: serializedName.value();
	}

	@SuppressWarnings("null")
	private boolean isOptional(Field f) {
		boolean ret = false;
		Optional annotation = f.getAnnotation(Optional.class);
		if (annotation == null) {
			ret = false;
		} else if (annotation.isOptional()) {
			ret = true;
		}
		return ret;
	}

	public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
		Class<? super T> raw = type.getRawType();

		if (!Object.class.isAssignableFrom(raw)) {
			return null; // it's a primitive!
		}

		ObjectConstructor<T> constructor = constructorConstructor.get(type);
		return new Adapter<T>(constructor, getBoundFields(gson, type, raw),
				excluder.getDisableOptionalAnnotation());
	}

	private ReflectiveTypeAdapterFactory.BoundField createBoundField(
			final Gson context, final Field field, final String name,
			final TypeToken<?> fieldType, boolean serialize,
			boolean deserialize, boolean isOptional) {
		final boolean isPrimitive = Primitives.isPrimitive(fieldType
				.getRawType());

		Method setter = null;
		Method getter = null;

		Accessor annotation = field.getAnnotation(Accessor.class);

		if (annotation != null) {
			Class<?> raw = field.getDeclaringClass();
			
			try {
				Class<?>[] arg = { field.getType() };
				setter = raw.getDeclaredMethod(
						annotation.Setter().equals("") ? "set" + name
								: annotation.Setter(), arg);
			} catch (NoSuchMethodException e) {
			}

			try {
				getter = raw.getDeclaredMethod(
						annotation.Getter().equals("") ? "get" + name
								: annotation.Getter(), new Class<?>[0]);
				if (!getter.getReturnType().equals(field.getType())) {
					getter = null;
				}
			} catch (NoSuchMethodException e) {
			}

		}

		final Method finalSetter = setter;
		final Method finalGetter = getter;

		// special casing primitives here saves ~5% on Android...
		return new ReflectiveTypeAdapterFactory.BoundField(name, serialize,
				deserialize, isOptional) {
			final TypeAdapter<?> typeAdapter = context.getAdapter(fieldType);

			@SuppressWarnings({ "unchecked", "rawtypes" })
			// the type adapter and field type always agree
			@Override
			void write(JsonWriter writer, Object value) throws IOException,
					IllegalAccessException {

				Object fieldValue;

				if (finalGetter != null) {
					try {
						fieldValue = finalGetter.invoke(value);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else {
					fieldValue = field.get(value);
				}
				
				TypeAdapter t = new TypeAdapterRuntimeTypeWrapper(context,
						this.typeAdapter, fieldType.getType());
				t.write(writer, fieldValue);
			}

			@Override
			void read(JsonReader reader, Object value) throws IOException,
					IllegalAccessException {
				Object fieldValue = typeAdapter.read(reader);
				if (fieldValue != null || !isPrimitive) {
					if (finalSetter != null) {
						try {
							finalSetter.invoke(value, fieldValue);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					} else {
						field.set(value, fieldValue);
					}
				}
			}
		};
	}

	private Map<String, BoundField> getBoundFields(Gson context,
			TypeToken<?> type, Class<?> raw) {
		Map<String, BoundField> result = new LinkedHashMap<String, BoundField>();
		if (raw.isInterface()) {
			return result;
		}

		Type declaredType = type.getType();
		while (raw != Object.class) {
			Field[] fields = raw.getDeclaredFields();
			for (Field field : fields) {
				boolean serialize = excludeField(field, true);
				boolean deserialize = excludeField(field, false);
				if (!serialize && !deserialize) {
					continue;
				}
				field.setAccessible(true);
				Type fieldType = $Gson$Types.resolve(type.getType(), raw,
						field.getGenericType());
				BoundField boundField = createBoundField(context, field,
						getFieldName(field), TypeToken.get(fieldType),
						serialize, deserialize, isOptional(field));
				BoundField previous = result.put(boundField.name, boundField);
				if (previous != null) {
					throw new IllegalArgumentException(declaredType
							+ " declares multiple JSON fields named "
							+ previous.name);
				}
			}
			type = TypeToken.get($Gson$Types.resolve(type.getType(), raw,
					raw.getGenericSuperclass()));
			raw = type.getRawType();
		}
		return result;
	}

	static abstract class BoundField {
		final String name;
		final boolean serialized;
		final boolean deserialized;
		final boolean isOptional;

		protected BoundField(String name, boolean serialized,
				boolean deserialized, boolean isOptional) {
			this.name = name;
			this.serialized = serialized;
			this.deserialized = deserialized;
			this.isOptional = isOptional;
		}

		abstract void write(JsonWriter writer, Object value)
				throws IOException, IllegalAccessException;

		abstract void read(JsonReader reader, Object value) throws IOException,
				IllegalAccessException;
	}

	public static final class Adapter<T> extends TypeAdapter<T> {
		private final ObjectConstructor<T> constructor;
		private final Map<String, BoundField> boundFields;
		private final boolean disableOptional;

		private Adapter(ObjectConstructor<T> constructor,
				Map<String, BoundField> boundFields, boolean disableOptional) {
			this.constructor = constructor;
			this.boundFields = boundFields;
			this.disableOptional = disableOptional;
		}

		@Override
		public T read(JsonReader in) throws IOException {
			List<String> transList = new ArrayList<String>();

			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}

			T instance = constructor.construct();

			try {
				in.beginObject();
				while (in.hasNext()) {
					String name = in.nextName();
					BoundField field = boundFields.get(name);
					if (field == null || !field.deserialized) {
						in.skipValue();
					} else {
						transList.add(field.name);
						field.read(in, instance);
					}
				}
//				if (!disableOptional) {
//					for (BoundField bf : boundFields.values()) {
//						if (transList.contains(bf.name)) {
//							continue;
//						} else {
//							if (!bf.isOptional) {
//								throw new AssertionError(
//										"field " + bf.name + " is missing in the package");
//							}
//						}
//					}
//				}
			} catch (IllegalStateException e) {
				throw new JsonSyntaxException(e);
			} catch (IllegalAccessException e) {
				throw new AssertionError(e);
			}
			in.endObject();
			return instance;
		}

		@Override
		public void write(JsonWriter out, T value) throws IOException {
			if (value == null) {
				out.nullValue();
				return;
			}

			out.beginObject();
			try {
				for (BoundField boundField : boundFields.values()) {
					if (boundField.serialized) {
						out.name(boundField.name);
						boundField.write(out, value);
					}
				}
			} catch (IllegalAccessException e) {
				throw new AssertionError();
			}
			out.endObject();
		}
	}
}

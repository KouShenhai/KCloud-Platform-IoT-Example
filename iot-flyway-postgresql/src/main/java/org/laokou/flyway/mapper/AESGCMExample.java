/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.flyway.mapper;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class AESGCMExample {

	private static final String AES = "AES";

	private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

	private static final int GCM_TAG_LENGTH = 16;

	private static final int GCM_IV_LENGTH = 12;

	public static void main(String[] args) throws Exception {

		String p = "^[a-zA-Z0-9_!@#$%^&*()\\-=+\\[\\]{};:'\",.<>/?|`~]{1,25}$";

		// 25
		// 72
		// 989
		// String originalText = "qwertyuiopasdfghjklzxcvbn";
		String originalText = "!!!!!!!!!!!!!!!!!!!!!!!!2";

		boolean matches = Pattern.matches(p, originalText);
		System.out.println(matches);

		// Generate key
		SecretKey secretKey = new SecretKeySpec("ME0taqrY4jyJ4nCMMnrOydriLbWKJOVl".getBytes(StandardCharsets.UTF_8),
				AES);

		byte[] iv = "bXxA1tbgDYbQ".getBytes(StandardCharsets.UTF_8);

		String encryptedText = encrypt(originalText, secretKey, iv);
		log.info("Encrypted Text: {}", encryptedText);

		System.out.println(originalText.length());
		System.out.println(encryptedText.length());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= originalText.length() - 4; i++) {
			String substring = originalText.substring(i, i + 4);
			sb.append(encrypt(substring, secretKey, iv)).append("~");
		}
		String string = sb.toString();
		System.out.println(string.substring(0, string.length() - 1).length());

		// Decrypt
		String decryptedText = decrypt(encryptedText, secretKey, iv);
		log.info("Decrypted Text: {}", decryptedText);
	}

	public static SecretKey generateKey(int keySize) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(AES);
		keyGen.init(keySize, new SecureRandom());
		return keyGen.generateKey();
	}

	public static byte[] generateIV() {
		byte[] iv = new byte[GCM_IV_LENGTH];
		new SecureRandom().nextBytes(iv);
		return iv;
	}

	public static String encrypt(String plainText, SecretKey key, byte[] iv) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
		GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		byte[] encryptedIVAndText = new byte[GCM_IV_LENGTH + encryptedBytes.length];
		System.arraycopy(iv, 0, encryptedIVAndText, 0, GCM_IV_LENGTH);
		System.arraycopy(encryptedBytes, 0, encryptedIVAndText, GCM_IV_LENGTH, encryptedBytes.length);
		return Base64.getEncoder().encodeToString(encryptedIVAndText);
	}

	public static String decrypt(String encryptedText, SecretKey key, byte[] iv) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(encryptedText);

		Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
		GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
		cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

		byte[] decryptedBytes = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

}

package com.world.storedevelopment.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ZenkakuUtilsTest {
	@Test
	public void test() {
		String origin = "ｶﾅ";
		String to = ZenkakuUtils.toZenkaku(origin);
		assertNotNull(to);

		assertEquals("カナ", to);
	}

	@Test
	public void test2() {
		String origin = "ｴｺｰﾙ･いずみ";
		String to = ZenkakuUtils.toZenkaku(origin);
		assertNotNull(to);

		assertEquals("エコール・いずみ", to);
	}

	@Test
	public void test3() {
		String origin = "ﾎﾞﾅﾝｻﾞﾌﾟﾗｻﾞ甚目寺ﾖｼｽﾞﾔ";
		String to = ZenkakuUtils.toZenkaku(origin);
		assertNotNull(to);

		assertEquals("ボナンザプラザ甚目寺ヨシズヤ", to);
	}

	@Test
	public void test4() {
		String origin = "ｱﾙﾋﾞ大阪（旧：ｷﾞｬﾚ大阪）";
		String to = ZenkakuUtils.toZenkaku(origin);
		assertNotNull(to);

		assertEquals("アルビ大阪（旧：ギャレ大阪）", to);

	}
}

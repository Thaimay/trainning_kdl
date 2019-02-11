package com.world.storedevelopment.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class HankakuUtilsTest {

	@Test
	public void test() {
		String origin = "カナ";
		String to = HankakuUtils.toHankaku(origin);
		assertNotNull(to);

		assertEquals("ｶﾅ", to);

	}

	@Test
	public void test2() {
		String origin = "エコール･いずみ";
		String to = HankakuUtils.toHankaku(origin);
		assertNotNull(to);

		assertEquals("ｴｺｰﾙ･いずみ", to);

	}

	@Test
	public void test3() {
		String origin = "ボナンザプラザ甚目寺ヨシズヤ";
		String to = HankakuUtils.toHankaku(origin);
		assertNotNull(to);

		assertEquals("ﾎﾞﾅﾝｻﾞﾌﾟﾗｻﾞ甚目寺ﾖｼｽﾞﾔ", to);

	}

	@Test
	public void test4() {
		String origin = "ｱﾙﾋﾞ大阪（旧：ｷﾞｬﾚ大阪）";
		String to = HankakuUtils.toHankaku(origin);
		assertNotNull(to);

		assertEquals("ｱﾙﾋﾞ大阪（旧：ｷﾞｬﾚ大阪）", to);

	}

}

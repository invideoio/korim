package com.soywiz.korim.bitmap

import com.soywiz.korim.color.*
import kotlin.test.*

class Bitmap32Test {
	@Test
	fun name() {
		val c = Bitmap32(1, 1)
		c[0, 0] = Colors.WHITE
		// @TODO: RGBA inline java.lang.AssertionError: expected:<-1> but was:<#ffffffff>
		//assertEquals(Colors.WHITE, c[0, 0])
		assertEquals(Colors.WHITE.hexString, c[0, 0].hexString)
	}

	@Test
	fun constructGen() {
		val c = Bitmap32(1, 1, Colors.BLUE, premultiplied = false)
		assertTrue(c.all { it == Colors.BLUE })
	}

	@Test
	fun fill() {
		val c = Bitmap32(16, 16)
		c.fill(Colors.RED)
		assertTrue(c.all { it == Colors.RED })
        assertTrue(c.any { it == Colors.RED })
        assertFalse(c.any { it == Colors.BLUE })
        c[0, 0] = Colors.BLUE
        assertFalse(c.all { it == Colors.RED })
        assertTrue(c.any { it == Colors.RED })
        assertTrue(c.any { it == Colors.BLUE })
	}

	//@Test
	//	//@Ignore
	//fun mipmaps() = suspendTest {
	//	val bmp = Bitmap32(4096 * 2, 4096 * 2, Colors.BLACK)
	//	val bitmap2 = bmp.mipmap(2)
	//	//awtShowImage(bitmap2); Thread.sleep(10000L)
	//}

    @Test
    fun bitmapsAreNotComparedNorHashedByContent() {
        val bmp1 = Bitmap32(32, 32)
        val bmp2 = Bitmap32(32, 32)
        val hash1 = bmp1.hashCode()
        val hash2 = bmp2.hashCode()
        assertNotEquals(bmp1, bmp2)
        assertNotEquals(hash1, hash2)

        bmp1[0, 0] = Colors.RED
        assertEquals(bmp1.hashCode(), hash1)
    }

    @Test
    fun testScaled() {
        val nbmp = Bitmap32(128, 128, Colors.RED).scaled(16, 16)
        assertEquals(16, nbmp.width)
        assertEquals(16, nbmp.height)
        assertEquals(Colors.RED, nbmp[0, 0])
        assertEquals(Colors.RED, nbmp[7, 7])
        assertEquals(Colors.RED, nbmp[15, 15])
    }
}

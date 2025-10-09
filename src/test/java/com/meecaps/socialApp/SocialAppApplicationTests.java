package com.meecaps.socialApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocialAppApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    public void simpleTest(){

        assertEquals(5,2+3);
        assertTrue(5>2);
        assertFalse(0>0);
        assertNotNull(5);
    }

    @ParameterizedTest
    @CsvSource({
            "10,20,30",
            "11,12,13",
            "20,10,10",
            "50,20,30",
            "11,22,33"

    })
    public void parameterTest(int a,int b, int target){
        assertEquals(target,a+b);
    }

}

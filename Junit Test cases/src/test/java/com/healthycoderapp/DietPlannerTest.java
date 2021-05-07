package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class DietPlannerTest {

	private DietPlanner dietPlanner;

	@BeforeEach
	// this setup() method will be invoked before each test
	void setup() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}

	@AfterEach
	void afterEach() {
		System.out.println("After each unit test");
	}

	@Test
	void should_ReturnCorrectDietPlan_When_CorrectCoder() {

		// Gender d = Gender.MALE;
		// System.out.println(d);
		// System.out.println(Gender.MALE.getClass().getSimpleName());
		// System.out.println(Gender.MALE);

		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);

		// when
		DietPlan actual = dietPlanner.calculateDiet(coder);

		// then
		// assertEquals(expected, actual);
		// this will throw an error because of different objects

		// alternate-1
		assertEquals(expected.toString(), actual.toString());

		// alternate-2
		assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getProtein(), actual.getProtein()),
				() -> assertEquals(expected.getFat(), actual.getFat()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));

	}

	// repeated test will repeat the test for certain no. of times
	@RepeatedTest(10)
	void should_ReturnCorrectDietPlan_When_CorrectCoder_Repeated() {

		// Gender d = Gender.MALE;
		// System.out.println(d);
		// System.out.println(Gender.MALE.getClass().getSimpleName());
		// System.out.println(Gender.MALE);

		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);

		// when
		DietPlan actual = dietPlanner.calculateDiet(coder);

		// then
		// assertEquals(expected, actual);
		// this will throw an error because of different objects

		// alternate-1
		assertEquals(expected.toString(), actual.toString());

		// alternate-2
		assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getProtein(), actual.getProtein()),
				() -> assertEquals(expected.getFat(), actual.getFat()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));

	}

	// repeated test will repeat the test for certain no. of times
	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
	void should_ReturnCorrectDietPlan_When_CorrectCoder_RepeatedWithTestName() {

		// Gender d = Gender.MALE;
		// System.out.println(d);
		// System.out.println(Gender.MALE.getClass().getSimpleName());
		// System.out.println(Gender.MALE);

		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);

		// when
		DietPlan actual = dietPlanner.calculateDiet(coder);

		// then
		// assertEquals(expected, actual);
		// this will throw an error because of different objects

		// alternate-1
		assertEquals(expected.toString(), actual.toString());

		// alternate-2
		assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getProtein(), actual.getProtein()),
				() -> assertEquals(expected.getFat(), actual.getFat()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));

	}
}
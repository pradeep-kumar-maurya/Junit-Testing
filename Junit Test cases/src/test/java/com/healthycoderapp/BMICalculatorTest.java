package com.healthycoderapp;

import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {

	private String environment = "dev";

	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all the unit tests");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("After all the unit tests");
	}

	// using parameterized test
	@ParameterizedTest
	@ValueSource(doubles = { 89.0, 95.0, 110.0 })
	void should_Return_True_When_DietRecommended_UsingParameterizedValues(double coderWeight) {

		// given
		double weight = coderWeight;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);

	}

	// using parameterized test
	@ParameterizedTest(name = "weight={0} height={1}")
	@CsvSource(value = { "89.0, 1.72", "95.0, 1.75", "110.0, 1.78" })
	void should_Return_True_When_DietRecommended_UsingParameterizedCsvValues(double coderWeight, double coderHeight) {

		// given
		double weight = coderWeight;
		double height = coderHeight;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);

	}

	// using parameterized test
	@ParameterizedTest(name = "weight={0} height={1}")
	@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
	void should_Return_True_When_DietRecommended_UsingParameterizedCsvFileSource(double coderWeight,
			double coderHeight) {

		// given
		double weight = coderWeight;
		double height = coderHeight;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);

	}

	@Test
	@DisplayName(">>>Test to check if Diet is Recommended<<<")
	void should_Return_True_When_DietRecommended() {

		// given
		double weight = 89.0;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}

	@Test
	void should_Return_False_When_DietRecommended() {

		// given
		double weight = 50.0;
		double height = 1.92;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertFalse(recommended);
	}

	@Test
	void should_ThrowArithmeticException_When_HeightZero() {

		// given
		double weight = 50.0;
		double height = 0.00;

		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(ArithmeticException.class, executable);
	}

	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListIsNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));

		// when
		Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertEquals(1.82, coderWorstBMI.getHeight());
		assertEquals(98.0, coderWorstBMI.getWeight());

		// now we will pass some incorrect data
		// assertEquals(1.85, coderWorstBMI.getHeight());
		// assertEquals(98.9, coderWorstBMI.getWeight());
		// we will see that only one assert was evaluated and not the other one

		// using assertAll
		// assertAll(() -> assertEquals(1.85, coderWorstBMI.getHeight()),
		// () -> assertEquals(98.9, coderWorstBMI.getWeight()));
	}

	// this test will check the execution of time
	@Test
	void should_ReturnCodeWithWorstBMI_In1Ms_When_CoderListHas1000Elements() {

		// this will skip the test
		assumeTrue(this.environment.equals("prod"));

		// given
		List<Coder> coders = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			coders.add(new Coder(1.0 + i, 10.0 + i));
		}

		// when
		Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertTimeout(Duration.ofMillis(5), executable);
	}

	@Test
	void should_ReturnNullWorstBMICoder_When_CoderListIsEmpty() {

		// given
		List<Coder> coders = new ArrayList<>();

		// when
		Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coderWorstBMI);
	}

	// this will nest all the related tests in one class
	@Nested
	class ReturnCoderWithWorstBMI {

		@Test
		void should_ReturnCoderWithWorstBMI_When_CoderListIsNotEmpty() {

			// given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));

			// when
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

			// then
			assertEquals(1.82, coderWorstBMI.getHeight());
			assertEquals(98.0, coderWorstBMI.getWeight());

			// now we will pass some incorrect data
			// assertEquals(1.85, coderWorstBMI.getHeight());
			// assertEquals(98.9, coderWorstBMI.getWeight());
			// we will see that only one assert was evaluated and not the other one

			// using assertAll
			// assertAll(() -> assertEquals(1.85, coderWorstBMI.getHeight()),
			// () -> assertEquals(98.9, coderWorstBMI.getWeight()));
		}

		// this test will check the execution of time
		@Test
		void should_ReturnCodeWithWorstBMI_In1Ms_When_CoderListHas1000Elements() {

			// this will skip the test
			assumeTrue(BMICalculatorTest.this.environment.equals("prod"));

			// given
			List<Coder> coders = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}

			// when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

			// then
			assertTimeout(Duration.ofMillis(5), executable);
		}

		@Test
		void should_ReturnNullWorstBMICoder_When_CoderListIsEmpty() {

			// given
			List<Coder> coders = new ArrayList<>();

			// when
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

			// then
			assertNull(coderWorstBMI);
		}

	}

	@Test
	void should_ReturnCorrectBMIScoreArray_When_CoderListIsNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));

		double[] expected = { 18.52, 29.59, 19.53 };

		// when
		double[] bmiScores = BMICalculator.getBMIScores(coders);

		// then
		assertArrayEquals(expected, bmiScores);
	}

	// it is a nested class which can hold same types of test under one class
	@Nested
	class GetBMIScores {
		@Test
		@DisplayName(">>>Test to calculate BMI")
		// it will disable the test on windows platform
		@DisabledOnOs(OS.WINDOWS)
		void should_ReturnCorrectBMIScoreArray_When_CoderListIsNotEmpty() {

			// given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));

			double[] expected = { 18.52, 29.59, 19.53 };

			// when
			double[] bmiScores = BMICalculator.getBMIScores(coders);

			// then
			assertArrayEquals(expected, bmiScores);
		}
	}
}

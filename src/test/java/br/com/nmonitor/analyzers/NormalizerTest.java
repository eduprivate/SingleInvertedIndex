package br.com.nmonitor.analyzers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class NormalizerTest {
	
	@Test
	public void specialCharactersRemovalTest() {
		// Give
		String title = "Demora em votação pode deixar imposto mais alto, diz \"Levy\"";
		String expectedTitle = "demora em votacao pode deixar imposto mais alto diz levy";
		Normalizer normalizer = new Normalizer();

		// When
		String normalizedTitle = normalizer.normalize(title);

		// Then
		Assert.assertEquals(expectedTitle, normalizedTitle);
	}
	
	@Test
	public void lowerCaseNormalizeTest() {
		// Give
		String title = "DEMORA EM VOTAÇÃO PODE DEIXAR IMPOSTO MAIS ALTO, DIZ LEVY";
		String expectedTitle = "demora em votacao pode deixar imposto mais alto diz levy";
		Normalizer normalizer = new Normalizer();

		// When
		String normalizedTitle = normalizer.normalize(title);

		// Then
		Assert.assertEquals(expectedTitle, normalizedTitle);
	}
	
	@Test
	public void normalizeListTest() {
		// Give
		String title0 = "DEMORA EM VOTAÇÃO PODE DEIXAR IMPOSTO MAIS ALTO, DIZ LEVY";
		String title1 = "Estátua de Tom Jobim tem parte danificada após brincadeira de jovem";
		String expectedTitle0 = "demora em votacao pode deixar imposto mais alto diz levy";
		String expectedTitle1 = "estatua de tom jobim tem parte danificada apos brincadeira de jovem";
		List<String> titles = new ArrayList<String>();
		titles.add(title0);
		titles.add(title1);
		Normalizer normalizer = new Normalizer();

		// When
		titles = normalizer.normalize(titles);

		// Then
		Assert.assertEquals(titles.get(0), expectedTitle0);
		Assert.assertEquals(titles.get(1), expectedTitle1);
	}

}

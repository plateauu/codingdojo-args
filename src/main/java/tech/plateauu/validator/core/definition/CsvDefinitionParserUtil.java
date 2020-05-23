package tech.plateauu.validator.core.definition;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.plateauu.validator.core.flag.FlagDefinition;
import tech.plateauu.validator.core.flag.MappedDefinition;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;

public class CsvDefinitionParserUtil {

	public static Definition parse(File file) {
		var records = parseFile(file);
		var flagDefinitions = new ArrayList<FlagDefinition>();

		for (CSVRecord record : records) {
			var definition = MappedDefinition.of(
					record.get("flag"),
					parseBoolean(record.get("operand")),
					parseBoolean(record.get("optional"))
			);
			flagDefinitions.add(definition);
		}

		return new Definition(flagDefinitions);
	}

	private static CSVParser parseFile(File file) {
		var csvFormat = CSVFormat.RFC4180
				.withFirstRecordAsHeader()
				.withIgnoreHeaderCase();
		try {
			FileReader fileReader = new FileReader(file);
			return csvFormat.parse(fileReader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

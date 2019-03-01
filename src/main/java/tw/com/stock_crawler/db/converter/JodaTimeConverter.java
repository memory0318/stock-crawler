package tw.com.stock_crawler.db.converter;

import org.joda.time.DateTime;
import org.springframework.data.convert.JodaTimeConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class JodaTimeConverter implements AttributeConverter<DateTime, Date> {

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public Date convertToDatabaseColumn(DateTime attribute) {
		return JodaTimeConverters.DateTimeToDateConverter.INSTANCE.convert(attribute);
	}

	@Override
	public DateTime convertToEntityAttribute(Date dbData) {
		return JodaTimeConverters.DateToDateTimeConverter.INSTANCE.convert(dbData);
	}
}

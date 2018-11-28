package br.com.football.players.deserializer;

import br.com.football.players.exception.ErrorCode;
import br.org.ehandler.exception.BadRequestException;
import br.org.ehandler.exception.message.Message;
import br.org.ehandler.exception.message.MessageDefault;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializerCustom extends StdDeserializer<LocalDate> {

  protected LocalDateDeserializerCustom() {
    super(LocalDate.class);
  }

  @Override
  public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    try {
      return new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE).deserialize(parser, context);

    } catch (Exception ex) {
      Message message = new MessageDefault(ErrorCode.FIELD_IS_INVALID, parser.currentName(), parser.getText().trim());
      throw new BadRequestException(message);
    }
  }
}

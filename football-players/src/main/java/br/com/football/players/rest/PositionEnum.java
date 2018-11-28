package br.com.football.players.rest;

import br.com.football.players.exception.ErrorCode;
import br.org.ehandler.exception.BadRequestException;
import br.org.ehandler.exception.message.MessageDefault;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum PositionEnum {

  LW(17, "LW"),
  ST(17, "ST"),
  RW(15, "RW"),
  LF(14, "LF"),
  CF(13, "CF"),
  RF(12, "RF"),
  CAM(11, "CAM"),
  LM(10, "LM"),
  CM(9, "CM"),
  RM(8, "RM"),
  CDM(7, "CDM"),
  LWB(6, "LWB"),
  LB(5, "LB"),
  CB(4, "CB"),
  RB(3, "RB"),
  RWB(2, "RWB"),
  GK(1, "GK");

  private Integer code;
  private String acronym;

  PositionEnum(Integer code, String acronym) {
    this.code = code;
    this.acronym = acronym;
  }

  public static PositionEnum convert(final String value) {
    return Stream.of(PositionEnum.values()).filter(v -> v.acronym.equalsIgnoreCase(value)).findFirst()
            .orElseThrow(() -> new BadRequestException(new MessageDefault(ErrorCode.FIELD_IS_INVALID, value)));
  }

}

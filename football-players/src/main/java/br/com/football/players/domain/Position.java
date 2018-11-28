package br.com.football.players.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "FOOTBALL_PLAYER",name = "positions")
public class Position {

    @Id
    @Column(name = "idt_position")
    private Integer id;
    @Size(max = 3)
    @Column(name = "nam_acronym")
    private String acronym;

}
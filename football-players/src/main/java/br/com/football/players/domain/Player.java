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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "FOOTBALL_PLAYER", name = "players")
@SequenceGenerator(name = "player_seq", schema = "FOOTBALL_PLAYER", sequenceName = "FOOTBALL_PLAYER.IDT_PLAYER_SQ", allocationSize = 1)
public class Player {

    @Id
    @Column(name = "idt_player")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    private Long id;
    @NotNull
    @Column(name = "cod_player")
    private UUID code;
    @Size(max = 30)
    @Column(name = "nam_name")
    private String name;
    @Size(max = 60)
    @Column(name = "nam_fullname")
    private String fullName;
    @Size(max = 30)
    @Column(name = "nam_nationality")
    private String nationality;
    @Column(name = "dat_borndate")
    private LocalDate bornDate;
    @Column(name = "flg_active")
    private Boolean active;
    @ManyToMany
    @JoinTable(name = "players_positions", schema = "FOOTBALL_PLAYER", joinColumns =
            {@JoinColumn(name = "idt_player")}, inverseJoinColumns =
            {@JoinColumn(name = "idt_position")})
    private Set<Position> positions;

}

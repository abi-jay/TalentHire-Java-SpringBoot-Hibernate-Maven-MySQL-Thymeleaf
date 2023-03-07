package org.abijay.talenthire.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "talents")
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Client Name can't be empty!")
    private String name;

    @Lob
    private String introduction;

    @Column(name = "talent")
    @NotNull(message = "Enter your talent")
    private String talent;
    private String location;
    private String email;
    private String url;

    @CreationTimestamp
    private LocalDate memberSince;
    private BigDecimal rate;

    // Many unique comments for a single table record
    // Bidirectional mapping where Talent is the owning side of this relationship
    // Whenever a talent record is deleted, hibernate should delete its reviews as well
    @OneToMany(mappedBy = "talent", cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();
}

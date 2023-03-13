/**
 *
 * * Filename: Talent.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
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

    @Column(name = "talent")
    @NotNull(message = "Enter your talent")
    private String talent;
    private String shortDescription;
    @Lob
    private String description;

    @CreationTimestamp
    private LocalDate memberSince;

    private BigDecimal rate;
    private String url;
    private String location;

    // One person can create Many talents
    // Primary key in User table becomes Foreign key Talent Table
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    /**
     *
     * * Many unique comments for a single table record
     * * Bidirectional mapping where Talent is the owning side of this relationship
     * * Whenever a talent record is deleted, hibernate should delete its requests as well
     */
    @OneToMany(mappedBy = "talent", cascade = CascadeType.REMOVE)
    private Set<Request> requests = new HashSet<>();

    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL)
    private Set<Fulfill> fulfills = new HashSet<>();
}

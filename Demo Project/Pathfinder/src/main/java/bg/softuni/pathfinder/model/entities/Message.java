package bg.softuni.pathfinder.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User author;

    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User recipient;
}

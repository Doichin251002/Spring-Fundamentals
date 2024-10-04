package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String GPXCoordinates;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "video_url")
    private String videoURL;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "route")
    private List<Comment> comments;

    @OneToMany(targetEntity = Picture.class, mappedBy = "route")
    private Set<Picture> pictures;

    @ManyToMany
    private Set<Category> categories;

    public Route() {
        this.comments = new ArrayList<>();
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }
}

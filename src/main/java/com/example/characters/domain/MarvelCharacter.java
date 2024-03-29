package com.example.characters.domain;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Data object of a marvel character")
public final class MarvelCharacter {

    @ApiModelProperty(notes = "Unique id of the character", example = "1017100")
    private final Long id;
    @ApiModelProperty(notes = "Name of the character", example = "A-Bomb (HAS)")
    private final String name;
    @ApiModelProperty(notes = "Descripttion about the character", example = "Rick Jones has been Hulk's "
        + "best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a "
        + "Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is "
        + "blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ")
    private final String description;
    @ApiModelProperty(notes = "Thumbnail for the character")
    private final ThumbNail thumbnail;

    private MarvelCharacter(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.thumbnail = builder.thumbnail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(final MarvelCharacter character) {
        return builder()
            .withId(character.id)
            .withName(character.getName())
            .withDescription(character.description)
            .withThumbNail(character.thumbnail);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ThumbNail getThumbnail() {
        return thumbnail;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarvelCharacter that = (MarvelCharacter) o;
        return Objects.equals(id, that.id)
            && Objects.equals(name, that.name)
            && Objects.equals(description, that.description)
            && Objects.equals(thumbnail, that.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, thumbnail);
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
            "id=" + id
            + ", name='" + name + '\''
            + ", description='" + description + '\''
            + ", thumbnail=" + thumbnail
            + '}';
    }

    public static final class Builder {

        private Long id;
        private String name;
        private String description;
        private ThumbNail thumbnail;

        private Builder() {
        }

        public Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withThumbNail(final ThumbNail thumbNail) {
            this.thumbnail = thumbNail;
            return this;
        }

        public MarvelCharacter build() {
            return new MarvelCharacter(this);
        }
    }

}

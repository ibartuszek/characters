package com.example.characters.domain;

import java.util.Objects;

public final class ThumbNail {

    private final String path;
    private final String extension;

    private ThumbNail(final Builder builder) {
        this.path = builder.path;
        this.extension = builder.extension;
    }

    public Builder builder() {
        return new Builder();
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ThumbNail thumbNail = (ThumbNail) o;
        return Objects.equals(path, thumbNail.path) &&
            Objects.equals(extension, thumbNail.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, extension);
    }

    @Override
    public String toString() {
        return "ThumbNail{"
            + "path='" + path + '\''
            + ", extension='" + extension + '\''
            + '}';
    }

    public static final class Builder {

        private String path;
        private String extension;

        private Builder() {
        }

        public Builder withPath(final String path) {
            this.path = path;
            return this;
        }

        public Builder withExtension(final String extension) {
            this.extension = extension;
            return this;
        }

        public ThumbNail build() {
            return new ThumbNail(this);
        }

    }

}

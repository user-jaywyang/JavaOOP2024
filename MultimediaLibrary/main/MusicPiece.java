package problem2;

import java.util.Objects;

/**
 * Represents a music piece, which can be created by either a recording artist or a band.
 * A music piece includes a title, the creator, and the year it was released.
 */
public class MusicPiece extends Item {

  /**
   * The recording artist who created the music piece, if applicable.
   */
  private RecordingArtist recordingArtist;

  /**
   * The band that created the music piece, if applicable.
   */
  private Band recordingBand;

  /**
   * Constructs a new MusicPiece with the specified title, recording artist, and release year.
   *
   * @param title           the title of the music piece.
   * @param recordingArtist the recording artist who created the music piece.
   * @param yearReleased    the year the music piece was released.
   */
  public MusicPiece(String title, RecordingArtist recordingArtist, int yearReleased) {
    super(title, recordingArtist, yearReleased);
    this.recordingArtist = recordingArtist;
  }

  /**
   * Constructs a new MusicPiece with the specified title, band, and release year.
   *
   * @param title         the title of the music piece.
   * @param recordingBand the band that created the music piece.
   * @param yearReleased  the year the music piece was released.
   */
  public MusicPiece(String title, Band recordingBand, int yearReleased) {
    super(title, recordingBand, yearReleased);
    this.recordingBand = recordingBand;
  }

  /**
   * Checks if the music piece was created by the given creator.
   *
   * @param creator the creator to check against.
   * @return true if the creator matches, false otherwise.
   */
  public boolean isCreatedBy(Creator creator) {
    return creator.equals(this.getCreator());
  }

  /**
   * Compares this MusicPiece to the specified object. The result is true if and only if the
   * argument is not null and is a MusicPiece object that has the same title, creator, and year released as this MusicPiece.
   *
   * @param o the object to compare this MusicPiece against.
   * @return true if the given object represents a MusicPiece equivalent to this MusicPiece, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicPiece that = (MusicPiece) o;
    return Objects.equals(this.getTitle(), that.getTitle()) &&
        Objects.equals(this.getCreator(), that.getCreator()) &&
        this.getYearReleased() == that.getYearReleased();
  }

  /**
   * Returns a hash code value for the MusicPiece. This method is supported for the benefit of hash tables.
   *
   * @return the hash code value for this MusicPiece.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getTitle(), this.getCreator(), this.getYearReleased());
  }

  /**
   * Returns a string representation of the MusicPiece.
   * The string includes the title, the creator's name, and the year released.
   *
   * @return a string representation of the MusicPiece.
   */
  @Override
  public String toString() {
    return "MusicPiece{title='" + getTitle() + "', creator=" + getCreator().getName() +
        ", yearReleased=" + getYearReleased() + "}";
  }
}

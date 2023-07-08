package com.example.filmust
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class MovieResponse (
    val page: Long,
    val next: String? = null,
    val entries: Long,
    val results: List<Movie>
)

@Serializable
data class Movie (
    @SerialName("_id")
    val id: String,

    @SerialName("id")
    val resultID: String,

    val ratingsSummary: ResultRatingsSummary? = null, //рейтинг
    val episodes: ResultEpisodes? = null,
    val primaryImage: PrimaryImage? = null,   //картинка
    val titleType: TitleType,
    val genres: Genres? = null,                       //жанры
    val titleText: TitleText,                 //название
    val originalTitleText: TitleText,
    val releaseYear: ReleaseYear,
    val releaseDate: ReleaseDate,             //дата выхода
    val runtime: Runtime? = null,             //длительность
    val series: JsonElement? = null,
    val meterRanking: MeterRanking? = null,   //рейтинг
    val plot: Plot? = null                    //описание
)

@Serializable
data class ResultEpisodes (
    val episodes: TotalEpisodesClass,
    val seasons: List<Season>,
    val years: List<Year>,
    val totalEpisodes: TotalEpisodesClass,
    val unknownSeasonEpisodes: TotalEpisodesClass,
    val topRated: TopRated,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class TotalEpisodesClass (
    val total: Long,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class Season (
    val number: Long,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class TopRated (
    val edges: List<Edge>,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class Edge (
    val node: Node,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class Node (
    val ratingsSummary: NodeRatingsSummary,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class NodeRatingsSummary (
    val aggregateRating: JsonElement? = null,

    @SerialName("__typename")
    val typename: RatingsSummaryTypename
)

@Serializable
enum class RatingsSummaryTypename(val value: String) {
    @SerialName("RatingsSummary") RatingsSummary("RatingsSummary");
}

@Serializable
data class Year (
    val year: Long,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class Genres (
    val genres: List<Genre>,

    @SerialName("__typename")
    val typename: GenresTypename
)

@Serializable
data class Genre (
    val text: String,
    val id: String,

    @SerialName("__typename")
    val typename: GenreTypename
)

@Serializable
enum class GenreTypename(val value: String) {
    @SerialName("Genre") Genre("Genre");
}

@Serializable
enum class GenresTypename(val value: String) {
    @SerialName("Genres") Genres("Genres");
}

@Serializable
data class MeterRanking (
    val currentRank: Long,
    val rankChange: RankChange,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class RankChange (
    val changeDirection: String,
    val difference: Long,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class TitleText (
    val text: String,

    @SerialName("__typename")
    val typename: OriginalTitleTextTypename
)

@Serializable
enum class OriginalTitleTextTypename(val value: String) {
    @SerialName("TitleText") TitleText("TitleText");
}

@Serializable
data class Plot (
    val plotText: PlotText,
    val language: Language,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class Language (
    val id: String,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class PlotText (
    val plainText: String,

    @SerialName("__typename")
    val typename: PlotTextTypename
)

@Serializable
enum class PlotTextTypename(val value: String) {
    @SerialName("Markdown") Markdown("Markdown");
}

@Serializable
data class PrimaryImage (
    val id: String,
    val width: Long,
    val height: Long,
    val url: String,
    val caption: PlotText,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class ResultRatingsSummary (
    val aggregateRating: JsonElement? = null,
    val voteCount: Long,

    @SerialName("__typename")
    val typename: RatingsSummaryTypename
)

@Serializable
data class ReleaseDate (
    val day: Long? = null,
    val month: Long,
    val year: Long,

    @SerialName("__typename")
    val typename: ReleaseDateTypename
)

@Serializable
enum class ReleaseDateTypename(val value: String) {
    @SerialName("ReleaseDate") ReleaseDate("ReleaseDate");
}

@Serializable
data class ReleaseYear (
    val year: Long,
    val endYear: JsonElement? = null,

    @SerialName("__typename")
    val typename: ReleaseYearTypename
)

@Serializable
enum class ReleaseYearTypename(val value: String) {
    @SerialName("YearRange") YearRange("YearRange");
}

@Serializable
data class Runtime (
    val seconds: Long,
    val displayableProperty: DisplayableProperty? = null,

    @SerialName("__typename")
    val typename: String
)

@Serializable
data class DisplayableProperty (
    val value: PlotText,

    @SerialName("__typename")
    val typename: DisplayablePropertyTypename
)

@Serializable
enum class DisplayablePropertyTypename(val value: String) {
    @SerialName("DisplayableTitleRuntimeProperty") DisplayableTitleRuntimeProperty("DisplayableTitleRuntimeProperty"),
    @SerialName("DisplayableTitleTypeProperty") DisplayableTitleTypeProperty("DisplayableTitleTypeProperty");
}

@Serializable
data class TitleType (
    val displayableProperty: DisplayableProperty? = null,
    val text: Text,
    val id: ID,
    val isSeries: Boolean,
    val isEpisode: Boolean,
    val categories: List<Category>? = null,
    val canHaveEpisodes: Boolean? = null,

    @SerialName("__typename")
    val typename: TitleTypeTypename
)

@Serializable
data class Category (
    val value: Value,

    @SerialName("__typename")
    val typename: CategoryTypename
)

@Serializable
enum class CategoryTypename(val value: String) {
    @SerialName("TitleTypeCategory") TitleTypeCategory("TitleTypeCategory");
}

@Serializable
enum class Value(val value: String) {
    @SerialName("movie") Movie("movie"),
    @SerialName("tv") Tv("tv");
}

@Serializable
enum class ID(val value: String) {
    @SerialName("movie") Movie("movie"),
    @SerialName("tvSeries") TvSeries("tvSeries");
}

@Serializable
enum class Text(val value: String) {
    @SerialName("Movie") Movie("Movie"),
    @SerialName("TV Series") TVSeries("TV Series");
}

@Serializable
enum class TitleTypeTypename(val value: String) {
    @SerialName("TitleType") TitleType("TitleType");
}
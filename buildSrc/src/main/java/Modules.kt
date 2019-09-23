/*
 * App Modules
 */
object Modules {

    private const val BASE_DIRECTORY = ":base"
    private const val SHOWS_DIRECTORY = ":shows"

    /*
     * App
     */
    const val app = ":app"

    /*
     * Base
     */
    const val core = "$BASE_DIRECTORY:core"
    const val corePresentation = "$BASE_DIRECTORY:core_presentation"
    const val coreDomain = "$BASE_DIRECTORY:core_domain"
    const val coreData = "$BASE_DIRECTORY:core_data"

    /*
     * Tv Shows
     */
    const val showsPresentation = "$SHOWS_DIRECTORY:shows_presentation"
    const val showsDomain = "$SHOWS_DIRECTORY:shows_domain"
    const val showsData = "$SHOWS_DIRECTORY:shows_data"
    const val showsDetailsPresentation = "$SHOWS_DIRECTORY:shows_details_presentation"
}
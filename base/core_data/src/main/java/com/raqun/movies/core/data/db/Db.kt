package com.raqun.movies.core.data.db

class Db private constructor() {

    object Config {
        const val DB_NAME = "movies"
        const val DB_VERSION = 1
        const val ID = "id"
        const val CREATE_DATE = "create_date"
        const val UPDATE_DATE = "update_date"
    }

    object TABLES {
        object TVSHOWS {
            const val NAME = "tv_shows"

            object COLUMNS {
                const val ID = Config.ID
                const val SHOW_NAME = "name"
                const val VOTE_AVARAGE = "vote_avarate"
                const val POSTER_PATH = "poster_path"
                const val CREATE_DATE = Config.CREATE_DATE
                const val UPDATE_DATE = Config.UPDATE_DATE

            }
        }

        object TVSHOW_DETAILS {
            const val NAME = "tv_show_details"

            object COLUMNS {
                const val ID = Config.ID
                const val SHOW_NAME = "name"
                const val VOTE_AVARAGE = "vote_avarate"
                const val POSTER_PATH = "poster_path"
                const val OVERVIEW = "overview"
                const val VOTE_COOUNT = "vote_count"
                const val CREATE_DATE = Config.CREATE_DATE
                const val UPDATE_DATE = Config.UPDATE_DATE
            }
        }

    }
}
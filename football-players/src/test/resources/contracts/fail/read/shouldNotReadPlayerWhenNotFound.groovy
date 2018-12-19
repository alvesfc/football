package fail.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not read player when not find in to database'
    name 'shouldNotReadPlayerWhenNotFound'
    label 'shouldNotReadPlayerWhenNotFound'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")),producer('/players/b2b0c1c6-2f27-4f41-b053-a0ca9222e6e9'))
    }

    response {
        status 404

        body([
                "errors": [
                        [
                                "code": "PLAYER_NOT_FOUND",
                                "message": "Player not found."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}

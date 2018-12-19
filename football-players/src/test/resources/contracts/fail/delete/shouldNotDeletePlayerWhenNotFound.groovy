package fail.delete

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not delete player when not find in to database'
    name 'shouldNotDeletePlayerWhenNotFound'
    label 'shouldNotDeletePlayerWhenNotFound'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.DELETE
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")),producer('/players/ae36db78-e78d-4b96-aefa-0666f927e957'))
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

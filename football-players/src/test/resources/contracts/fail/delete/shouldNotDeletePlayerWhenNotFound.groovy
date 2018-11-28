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
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.DELETE
        url value(consumer(regex('/players/[0-9]{1,18}')), producer('/players/1'))
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
            contentType(applicationJson())
        }
    }
}

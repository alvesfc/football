package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create transfer with team not found.'
    name 'shouldNotCreateTransferWithTeamNotFound'
    label 'shouldNotCreateTransferWithTeamNotFound'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/transfers')), producer('/transfers'))
        body([
                "team"         : value(consumer(regex(new RegexPatterns().uuid())), producer(anyUuid())),
                "player"       : value(consumer(regex(new RegexPatterns().uuid())), producer(anyUuid())),
                "value"        : value(consumer(regex('^[0-9]{1,18}$')), producer(2500000000)),
                "wage"         : value(consumer(regex('^[0-9]{1,18}$')), producer(60000000)),
                "contractUntil": value(consumer(regex('^[0-9]{4}$')), producer(2020))
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                                "code"   : "TEAM_NOT_FOUND",
                                "message": "Team not found."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}



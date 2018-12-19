package fail.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not retrieve team when not found.'
    name 'shouldNotReadTeam'
    label 'shouldNotReadTeam'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/teams/${new RegexPatterns().uuid().toString()}")), producer('/teams/3b241101-e2bb-4255-8caf-4136c566a962'))
    }

    response {
        status 404

        body([
                "errors": [
                        [
                                "code": "TEAM_NOT_FOUND",
                                "message": "Team not found."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}


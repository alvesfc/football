package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create team without fullName.'
    name 'shouldNotCreateTeamWithoutFullname'
    label 'shouldNotCreateTeamWithoutFullname'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/teams')), producer('/teams'))

        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('Arsenal')),
                "acronym": value(consumer(regex('^.{2,3}$')), producer('ARS')),
                "country": value(consumer(regex('^.{3,30}$')), producer('England'))
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_MANDATORY",
                            "message": "The field fullName is required."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}



package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create team with invalid fullname.'
    name 'shouldNotCreateTeamWithInvalidFullname'
    label 'shouldNotCreateTeamWithInvalidFullname'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/teams')), producer('/teams'))

        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('Arsenal')),
                "fullName"       : value(consumer(regex('^((?!^.{3,30}$).)*$')), producer('Ar')),
                "acronym": value(consumer(regex('^.{2,3}$')), producer('ARS')),
                "country": value(consumer(regex('^.{3,30}$')), producer('England'))
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_INVALID",
                            "message": "Field fullName with value (Ar) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}



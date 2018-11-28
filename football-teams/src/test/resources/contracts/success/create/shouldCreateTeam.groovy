package success.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should create team when submitting all valid information.'
    name 'shouldCreateTeam'
    label 'shouldCreateTeam'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/teams')), producer('/teams'))
        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('Arsenal')),
                "fullName"       : value(consumer(regex('^.{3,30}$')), producer('Arsenal Football Club')),
                "acronym": value(consumer(regex('^.{2,3}$')), producer('ARS')),
                "country": value(consumer(regex('^.{3,30}$')), producer('England'))
        ])
    }

    response {
        status 201

        body([
                "code": value(consumer('1fb6264e-2e24-4948-8bbd-33648f7aeb38'), producer(regex(new RegexPatterns().uuid().toString())))
        ])

        headers {
            contentType(applicationJson())
            header("location", regex("/teams/${new RegexPatterns().uuid().toString()}"))
        }
    }
}



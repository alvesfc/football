package success.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should retrieve team when submitting all valid information.'
    name 'shouldReadTeam'
    label 'shouldReadTeam'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/teams/${new RegexPatterns().uuid().toString()}")), producer('/teams/3b241101-e2bb-4255-8caf-4136c566a962'))
    }

    response {
        status 200

        body([
                "name"   : value(consumer('Arsenal'), producer(regex('^.{3,30}$'))),
                "fullName"   : value(consumer('Arsenal Football Club'), producer(regex('^.{3,30}$'))),
                "acronym": value(consumer('ARS'), producer(regex('^.{2,3}$'))),
                "country": value(consumer('England'), producer(regex('^.{3,30}$'))),
                "active" : value(consumer('true'), producer(regex(new RegexPatterns().anyBoolean()))),

        ])
    }
}


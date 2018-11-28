package success.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should update team when submitting all valid information.'
    name 'shouldUpdateTeam'
    label 'shouldUpdateTeam'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex("/teams/${new RegexPatterns().uuid().toString()}")), producer('/teams/e62f1384-f2f0-4817-a0b8-49a24456a532'))
        body([
                "name"   : value(consumer(regex('^.{3,30}$')), producer('Arsenal')),
                "fullName"   : value(consumer(regex('^.{3,30}$')), producer('Arsenal Football Club')),
                "acronym": value(consumer(regex('^.{2,3}$')), producer('ARS')),
                "country": value(consumer(regex('^.{3,30}$')), producer('England'))
        ])

    }

    response {
        status 200
    }
}



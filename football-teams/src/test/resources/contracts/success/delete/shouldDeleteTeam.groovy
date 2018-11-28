package success.delete

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should delete team when submitting all valid information.'
    name 'shouldDeleteTeam'
    label 'shouldDeleteTeam'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.DELETE
        url value(consumer(regex("/teams/${new RegexPatterns().uuid().toString()}")), producer('/teams/4614eb6f-bcc8-498c-8013-09e687e08d69'))
    }

    response {
        status 200
    }
}



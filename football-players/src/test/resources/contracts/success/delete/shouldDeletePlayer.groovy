package success.delete

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should delete player when submitting all valid information.'
    name 'shouldDeletePlayer'
    label 'shouldDeletePlayer'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.DELETE
        url value(consumer(regex('/players/[0-9]{1,18}')), producer('/players/15'))
    }

    response {
        status 200
    }
}



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
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.DELETE
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")), producer('/players/78ef0dd9-af1b-4580-be67-59246a71298e'))
    }

    response {
        status 200
    }
}



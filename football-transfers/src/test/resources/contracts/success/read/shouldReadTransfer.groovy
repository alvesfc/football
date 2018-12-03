package success.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should retrieve transfer when submitting all valid information.'
    name 'shouldReadTransfer'
    label 'shouldReadTransfer'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/transfers/${new RegexPatterns().uuid()}")), producer("/transfers/966961cf-cc62-4f10-ae62-05b81cc687a8"))

    }

    response {
        status 200

        body([
                "team"         : value(consumer(anyUuid()), producer(regex(new RegexPatterns().uuid()))),
                "player"       : value(consumer(anyUuid()), producer(regex(new RegexPatterns().uuid()))),
                "value"        : value(consumer(2500000000), producer(regex('^[0-9]{1,18}$'))),
                "wage"         : value(consumer(60000000), producer(regex('^[0-9]{1,18}$'))),
                "contractUntil": value(consumer(2020), producer(regex('^[0-9]{4}$')))
        ])

        headers {
            contentType(applicationJson())
        }
    }
}



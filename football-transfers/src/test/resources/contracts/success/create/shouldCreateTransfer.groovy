package success.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should create transfer when submitting all valid information.'
    name 'shouldCreateTransfer'
    label 'shouldCreateTransfer'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/transfers')), producer('/transfers'))
        body([
                "team"       : value(consumer(regex(new RegexPatterns().uuid())), producer(anyUuid())),
                "player"       : value(consumer(regex(new RegexPatterns().uuid())), producer(anyUuid())),
                "value": value(consumer(regex('^[0-9]{1,18}$')), producer(2500000000)),
                "wage": value(consumer(regex('^[0-9]{1,18}$')), producer(60000000)),
                "contractUntil": value(consumer(regex('^[0-9]{4}$')), producer(2020))
        ])
    }

    response {
        status 201

        body([
                "id": value(consumer('1fb6264e-2e24-4948-8bbd-33648f7aeb38'), producer(regex(new RegexPatterns().uuid().toString())))
        ])

        headers {
            contentType(applicationJson())
            header("location", regex("/transfers/${new RegexPatterns().uuid().toString()}"))
        }
    }
}



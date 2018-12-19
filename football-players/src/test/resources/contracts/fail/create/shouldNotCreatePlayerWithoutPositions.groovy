package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create player whithout positions.'
    name 'shouldNotCreatePlayerWithoutPositions'
    label 'shouldNotCreatePlayerWithoutPositions'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "name": value(consumer(optional(regex(new RegexPatterns().nonBlank()))), producer('Neymar')),
                "fullName"   : value(consumer(optional(regex(new RegexPatterns().nonBlank()))), producer('Neymar da Silva Santos Jr. ')),
                "nationality": value(consumer(regex(new RegexPatterns().nonBlank())), producer('Brazil')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1992-05-02'))
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "VALUE_GREATER_EQUAL_THAN_ONE",
                            "message": "The value of the positions field must be greater than or equal to one."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}



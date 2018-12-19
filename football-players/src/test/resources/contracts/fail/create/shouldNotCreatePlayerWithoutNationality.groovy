package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create player whithout nationality.'
    name 'shouldNotCreatePlayerWithoutNationality'
    label 'shouldNotCreatePlayerWithoutNationality'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "name"       : value(consumer(regex(new RegexPatterns().nonBlank())), producer('Neymar')),
                "fullName"   : value(consumer(optional(regex(new RegexPatterns().nonBlank()))), producer('Neymar da Silva Santos Jr. ')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1992-05-02')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('LW'))
                ]
        ])

    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_MANDATORY",
                            "message": "The field nationality is required."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}



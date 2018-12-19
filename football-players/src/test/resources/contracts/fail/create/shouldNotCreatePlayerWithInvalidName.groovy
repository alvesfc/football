package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create player with invalida name'
    name 'shouldNotCreatePlayerWithInvalidName'
    label 'shouldNotCreatePlayerWithInvalidName'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "name"       : value(consumer(regex('^((?!^.{3,30}$).)*$')), producer('A. Jahanbakhsh Jahanbakhsh Jahanbakhsh')),
                "fullName"   : value(consumer(optional(regex('^.{6,60}$'))), producer('Alireza Jahanbakhsh ')),
                "nationality": value(consumer(regex('^.{3,30}$')), producer('Iran')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1993-10-08')),
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
                            "code": "FIELD_IS_INVALID",
                            "message": "Field name with value (A. Jahanbakhsh Jahanbakhsh Jahanbakhsh) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}



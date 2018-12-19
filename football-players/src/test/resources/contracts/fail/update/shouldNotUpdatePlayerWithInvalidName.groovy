package fail.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not update player with invalid name'
    name 'shouldNotUpdatePlayerWithInvalidName'
    label 'shouldNotUpdatePlayerWithInvalidName'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")),producer('/players/ab74980b-28cd-44b2-8cc0-c51ff0a336d8'))
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



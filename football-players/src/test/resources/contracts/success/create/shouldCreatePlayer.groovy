package success.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should create player when submitting all valid information.'
    name 'shouldCreatePlayer'
    label 'shouldCreatePlayer'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('Neymar')),
                "fullName"   : value(consumer(optional(regex('^.{6,60}$'))), producer('Neymar da Silva Santos Jr. ')),
                "nationality": value(consumer(regex('^.{3,30}$')), producer('Brazil')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1992-05-02')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('LW'))
                ]
        ])

    }

    response {
        status 201

        body([
                "code": value(consumer('10'), producer(regex('[+]?[0-9]{1,18}')))
        ])

        headers {
            contentType(applicationJson())
            header("location", regex('/players/([+]?[0-9]{1,18})'))
        }
    }
}



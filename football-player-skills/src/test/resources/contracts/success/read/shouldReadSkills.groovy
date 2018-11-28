package success.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should read  skills for a player when submitting all valid information.'
    name 'shouldReadSkills'
    label 'shouldReadSkills'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/skills/${new RegexPatterns().uuid()}\\?season=[0-9]{4}-[0-9]{4}")), producer('/skills/319626f3-d429-4d10-ac64-bd7b6fde53e7?season=2017-2018'))


    }

    response {
        status 200

        body([
                "playerID"    : value(consumer(any()), producer(regex(new RegexPatterns().uuid()))),
                "season"      : value(consumer('2017-2018'), producer(regex('^.{3,30}$'))),
                "traits"      : [
                        value(consumer('Diver'), producer(regex('^.{3,30}$'))),
                        value(consumer('Long Shot Taker'), producer(regex('^.{3,30}$')))
                ],
                "specialities": [
                        value(consumer('Dribbler'), producer(regex('^.{3,30}$')))
                ],
                "attributes"  : [
                        [
                                "category": value(consumer('Physical'), producer(regex('^.{3,30}$'))),
                                "values"  : [
                                        [
                                                "name" : value(consumer('Acceleration'), producer(regex('^.{3,30}$'))),
                                                "value": value(consumer(80), producer(regex('[0-9]{1,2}'))),

                                        ],
                                        [
                                                "name" : value(consumer('Agility'), producer(regex('^.{3,30}$'))),
                                                "value": value(consumer(78), producer(regex('[0-9]{1,2}'))),

                                        ]
                                ]
                        ],
                        [
                                "category": value(consumer('Technical'), producer(regex('^.{3,30}$'))),

                                "values"  : [
                                        [
                                                "name" : value(consumer('Ball Control'), producer(regex('^.{3,30}$'))),
                                                "value": value(consumer(86), producer(regex('[0-9]{1,2}'))),

                                        ]
                                ]
                        ]
                ]
        ])
    }
}



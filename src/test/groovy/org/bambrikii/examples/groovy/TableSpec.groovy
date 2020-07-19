package org.bambrikii.examples.groovy

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

import spock.lang.Specification
import spock.lang.Unroll

class TableSpec extends Specification {
	@Unroll
	def "should add"(){
		given:
		def a=1
		and:
		def b=2
		expect:
		a + b == 3
	}
	@Unroll
	def "should process table #a #b #c"(a, b, c){
		given:
		a>0
		and:
		b>0
		expect:
		a+b == c
		where:
		a|b|c
		1|2|3
		4|5|9
	}
}

TChoose {
	*kr { arg trig, array;
		^Select.kr(TRand.kr(0, array.size, trig), array)
	
	}
}


TWChoose : UGen {
	*ar { arg trig, array, weights, normalize=0;
		^Select.ar(TWindex.ar(trig, weights, normalize), array)
	}
	*kr { arg trig, array, weights, normalize=0;
		^Select.kr(TWindex.kr(trig, weights, normalize), array)
	}

}

Channel  {

	*new { arg rate, input, offset, numChannels, wrap=true;
		var which, size;
		size = input.size;
		which = Array.fill(numChannels, { arg i;
					var j;
					j = offset + i;
					if(wrap) { j % size } { j.min(size - 1) }
		});
		^Select.multiNewList([rate, which] ++ input);
	
	}

	*ar { arg input, offset, numChannels, wrap=true;
		^this.new('audio', input, offset, numChannels, wrap)
	}
	*kr { arg input, offset, numChannels, wrap=true;
		^this.new('control', input, offset, numChannels, wrap)
	}
}



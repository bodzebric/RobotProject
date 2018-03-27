package nl.hva.miw.robot.utilities;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractCalibrationFilter;


public class NormalizationFilter extends AbstractCalibrationFilter {
	
	/** Array of minimum output range limit specified by the user */
	protected float[] minOut;

	/** Array of maximum output range limit specified by the user */
	protected float[] maxOut;
	
	/** The scale factor calculated from the calibrated minimum and maximum
	 * readings */
	protected float[] scale;
	
	/** Whether to clamp the values to the output range (true), or allow
	 * values outside the range if they fall outside the calibration zone
	 * (false) */
	protected boolean clamp;

	
	/**
	 * <p>
	 * Constructs a new filter wrapping the given source, as well as
	 * using all the fully-specified range parameters for the samples.
	 * </p>
	 *
	 * @param source The sample provider to wrap
	 * @param minOutput The minimum values to return for normalized samples
	 * @param maxOutput The maximum values to return for normalized samples
	 * @param clamp Whether to limit all normalized samples to the min-max
	 *              range (true) or allow samples to go outside the range
	 *              if the raw samples are outside the calibrated range (false).
	 */
	public NormalizationFilter(SampleProvider source,
			float[] minOutput, float[] maxOutput, boolean clamp) {
		super(source);
		
		if (minOutput.length != sampleSize || maxOutput.length != sampleSize) {
			throw new IllegalArgumentException
			("NormalizationFilter range arrays must be the same length as the sample size.");
		}
		
		minOut = minOutput;
		maxOut = maxOutput;
		scale = new float[sampleSize];
		for (int i = 0; i < sampleSize; i++) {
			scale[i] = 1;
		}
		this.clamp = clamp;
	}

	
	/**
	 * <p>
	 * Constructs a new filter wrapping the given source, using the single
	 * min and max values for all data points in the sample.
	 * </p>
	 */
	public NormalizationFilter(SampleProvider source,
			float minOutputAll, float maxOutputAll, boolean clamp) {
		this(source,
				new float[source.sampleSize()],
				new float[source.sampleSize()],
				clamp);
		for (int i = 0; i < sampleSize; i++) {
			minOut[i] = minOutputAll;
			maxOut[i] = maxOutputAll;
		}
	}
	
	
	/**
	 * <p>
	 * Constructs a new filter wrapping the given source, using the single
	 * min and max values for all data points in the sample.  Clamping
	 * is set to false.
	 * </p>
	 */
	public NormalizationFilter(SampleProvider source,
			float minOutputAll, float maxOutputAll) {
		this(source, minOutputAll, maxOutputAll, false);
	}
	
	
	/**
	 * <p>
	 * Constructs a new filter wrapping the given source, using 0 as the
	 * minimum and 1 as the maximum values for all data points in the 
	 * sample.
	 * </p>
	 */
	public NormalizationFilter(SampleProvider source, boolean clamp) {
		this(source, 0, 1, clamp);
	}
	

	/**
	 * <p>
	 * Constructs a new filter wrapping the given source, using 0 as the
	 * minimum and 1 as the maximum values for all data points in the 
	 * sample.  Clamping is set to false.
	 * </p>
	 */
	public NormalizationFilter(SampleProvider source) {
		this(source, false);
	}

	
	/**
	 * Fetches a sample from the sensor and updates array with minimum and maximum values when
	 * the calibration process is running.
	 */
	public void fetchSample(float[] dst, int off) {
		super.fetchSample(dst, off);
		
		if (!calibrating) {
			for (int i = 0; i < sampleSize; i++) {
				dst[i + off] = (dst[i + off] - min[i]) * scale[i];
				if (clamp) {
					if (dst[i + off] > maxOut[i]) {
						dst[i + off] = maxOut[i];
					}
					if (dst[i + off] < minOut[i]) {
						dst[i + off] = minOut[i];
					}
				}
			}
		}
	}


	/* (non-Javadoc)
	 * @see lejos.robotics.filter.AbstractCalibrationFilter#startCalibration()
	 */
	@Override
	public void startCalibration() {
		super.startCalibration();
		// (re)set the scale back to one
		for (int i = 0; i < sampleSize; i++) {
			scale[i] = 1;
		}
	}


	/* (non-Javadoc)
	 * @see lejos.robotics.filter.AbstractCalibrationFilter#stopCalibration()
	 */
	@Override
	public void stopCalibration() {
		super.stopCalibration();
		
		// calculate the scale based on the observed min/max and
		// the desired output min/max
		for (int i = 0; i < sampleSize; i++) {
			scale[i] = (maxOut[i] - minOut[i]) / (max[i] - min[i]);
		}
	}

}
package com.github.binarywang;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * @author Binary Wang
 */
public abstract class BaseTestCase {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
package com.artemis.covidtestingplatform;

import com.artemis.covidtestingplatform.controllers.PatientController;
import com.artemis.covidtestingplatform.models.Patient;
import com.artemis.covidtestingplatform.services.GeoCodeService;
import com.artemis.covidtestingplatform.services.PatientService;
import com.artemis.covidtestingplatform.services.ScheduledAppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    PatientService patientService;

    @Mock
    GeoCodeService geoCodeService;

    @Mock
    ScheduledAppointmentService scheduledAppointmentService;

    @Test
    public void testSave() {
        Patient patient = new Patient();
        Mockito.when(patientService.save(Mockito.any())).thenReturn(patient);
        patientController.save(patient);
        Mockito.verify(patientService, Mockito.times(1)).save(patient);
    }


}

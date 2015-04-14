package Model.Impl;

import Hotel.Booking;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by KaspYar on 08.04.2015.
 */
public class ImplBookingDAOTest {

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }



    @Test(expected = SQLException.class)
    public void cancelBookingNegativeTest() throws SQLException {
        ImplBookingDAO target = new ImplBookingDAO(connectionMock);
        Booking bookingLocalMock = new Booking(1,1,null,null);
        String query = "DELETE FROM booking WHERE idBooking = ?";

        when(connectionMock.prepareStatement(query)).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

        target.cancelBooking(bookingLocalMock);
    }



    @Test
    public void cancelBookingPositiveTest() throws SQLException {
        ImplBookingDAO target = new ImplBookingDAO(connectionMock);
        Booking bookingLocalMock = new Booking(1,1,null,null);
        String query = "DELETE FROM booking WHERE idBooking = ?";

        when(connectionMock.prepareStatement(query)).thenReturn(preparedStatementMock);

        target.cancelBooking(bookingLocalMock);

        verify(connectionMock, times(1)).prepareStatement(eq(query));
        verify(preparedStatementMock).setInt(eq(1), eq(1));
        verify(preparedStatementMock).executeUpdate();




    }



}

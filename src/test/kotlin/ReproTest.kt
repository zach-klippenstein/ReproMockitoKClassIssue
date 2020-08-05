import io.mockk.mockk
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.reflect.full.isSuperclassOf
import kotlin.test.assertTrue

interface Interface

class ReproTest {

    private val expectedClass = Interface::class
    private val expectedJavaClass = Interface::class.java

    // This test fails.
    @Test
    fun `using kotlin reflection with mockito`() {
        val mockitoImplementation = mock(Interface::class.java)
        val mockitoClass = mockitoImplementation::class
        assertTrue(expectedClass.isSuperclassOf(mockitoClass))
    }

    // This test fails.
    @Test
    fun `using kotlin reflection with mockk`() {
        val mockkImplementation = mockk<Interface>()
        val mockkClass = mockkImplementation::class
        assertTrue(expectedClass.isSuperclassOf(mockkClass))
    }

    // This test passes.
    @Test
    fun `using java reflection with mockito`() {
        val mockitoImplementation = mock(Interface::class.java)
        val mockitoJavaClass = mockitoImplementation::class.java
        assertTrue(expectedJavaClass.isAssignableFrom(mockitoJavaClass))
    }

    // This test passes.
    @Test
    fun `using java reflection with mockk`() {
        val mockkImplementation = mockk<Interface>()
        val mockkJavaClass = mockkImplementation::class.java
        assertTrue(expectedJavaClass.isAssignableFrom(mockkJavaClass))
    }
}

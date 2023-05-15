fun main() {
    val carFactory = CarFactory()
    var selectNumber = 0

    do {
        selectNumber = carFactory.selectCarType()
        if (selectNumber != 0) {
            val car = carFactory.createCar(selectNumber)

            carFactory.addToy(car)
        }

    } while (selectNumber != 0)
    carFactory.printCarCount()
    carFactory.printCarInfo()
    carFactory.printToyTotalAvgPrice()
    carFactory.printBoarding()
    carFactory.printuel()

}

class CarFactory() {

    val carList = ArrayList<Car>()

    fun selectCarType(): Int {

        var selectNumber = 0

        do {
            println("생산할 장난감의 종류를 선택해주세요")
            print("1. 붕붕, 2. 승용차, 3. 버스, 4. 트럭, 0. 생산종료 : ")
            selectNumber = readln().toInt()

            if (selectNumber !in 0..4) {
                println("잘못 입력하였습니다")
            }
        } while (selectNumber !in 0..4)

        return selectNumber
    }

    fun printCarCount() {

        var bungCount = 0
        var carsCount = 0
        var busCount = 0
        var truckCount = 0
        println()
        println("총 생산 자동차 수 : ${carList.size} 개")


        for (tempCar in carList) {
            when (tempCar.type) {
                "붕붕" -> bungCount++
                "승용차" -> carsCount++
                "버스" -> busCount++
                "트럭" -> truckCount++
            }
        }
        println()
        println("붕붕 : $bungCount 개")
        println("승용차 : $carsCount 개")
        println("버스 : $busCount 개")
        println("트럭 : $truckCount 개")
        println()
    }

    fun printCarInfo() {
        // 장난감의 수 만큼 반복한다.
        for (tempCar in carList) {
            tempCar.printCarInfo()
        }
    }

//    생산된 자동차들의 평균 속도 : 000km/h
//    생산된 자동차들의 총 탑승인원수 : 0000명
//    연료가 꽃향기인 자동차의 수 : 00대
//    연료가 휘발유인 자동차의 수 : 00대
//    연료가 가스인 자동차의 수 : 00대

    fun printToyTotalAvgPrice() {
        var speedTotal = 0

        for (tempCar in carList) {
            speedTotal += tempCar.speed
        }
        // 평균을 구한다.
        val speedAvg = speedTotal / carList.size

        println("생산된 자동차들의 평균 속도 : ${speedAvg}km/h")

    }

    fun printuel() {
        var totalFlower = 0
        var totalGasoline = 0
        var gas = 0

        for (tempCar in carList) {
            if (tempCar.fuel == "꽃향기") {
                totalFlower += 1
            } else if (tempCar.fuel == "휘발유") {
                totalGasoline += 1
            } else {
                gas += 1
            }
        }
        println("연료가 꽃향기인 자동차의 수 : $totalFlower 대")
        println("연료가 휘발유인 자동차의 수 : $totalGasoline 대")
        println("연료가 가스인 자동차의 수 : $gas 대")

    }

    fun printBoarding() {
        var totalBoarding = 0

        for (tempCar in carList) {
            totalBoarding += tempCar.personnel
        }
        println("생산된 자동차들의 총 탑승인원수 : $totalBoarding 명")
    }


    fun createCar(CarType: Int) = when (CarType) {
        1 -> Car("붕붕", 300, "꽃향기", 1)
        2 -> Car("승용차", 200, "휘발유", 4)
        3 -> Car("버스", 150, "가스", 50)
        4 -> Car("트럭", 100, "가스", 3)
        else -> Car("", 0, "", 0)
    }

    // 생산된 장난감을 저장한다.
    fun addToy(toy: Car) {
        carList.add(toy)
    }

}

class Car(var type: String, var speed: Int, var fuel: String, var personnel: Int) {

    fun printCarInfo() {

        println("종류 : $type")
        println("최대속도 : $speed km/h")
        println("연료 : $fuel")
        println("탑승인원수 : ${personnel}명")
        println()
    }
}
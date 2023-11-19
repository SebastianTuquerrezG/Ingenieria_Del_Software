'use strict'

const amqp = require('amqplib')
const queue = process.env.QUEUE || 'hello'

const messagesAmount = 6
const wait = 400

function sleep (ms) {
  return new Promise(resolve => setTimeout(resolve, ms))
}

async function sleepLoop(num, cb) {
    while (num--) {
        await sleep(wait)

        cb()
    }
}

async function exitAfterSend () {
    await sleep(messagesAmount * wait + 1.2)

    process.exit(0)
}

async function subscriber(){
    const conn = await amqp.connect('amqp://localhost')
    const channel = await conn.createChannel()
    await channel.assertQueue(queue, {
        durable: true
    })

    await sleepLoop(messagesAmount, async () => {
        const message = {
            id: Math.random().toString(16).slice(2, 6),
            text: 'Hello world'
        }

        const sent = await channel.sendToQueue(queue, Buffer.from(JSON.stringify(message)),
            { persistent: true })

        sent ?
            console.log(`Message sent: to "${queue}" queue`, message) :
            console.log(`Message NOT sent: to "${queue}" queue`, message)
    })
}

subscriber().catch(error => {
    console.error(error)
    process.exit(1)
})

exitAfterSend().catch(error => {
    console.error(error)
    process.exit(1)
})